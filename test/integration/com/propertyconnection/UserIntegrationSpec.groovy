package com.propertyconnection

import spock.lang.*

class UserIntegrationSpec extends Specification {
    Date newDate = new Date()

        def "Saving a user to the database" () {
                given: "A new user"
                def charlie = new User(
                        dateCreated:  new Date(),
                        password:  'password',
                        loginId: 'charles'

                )

                when: "The user is saved"
                charlie.save()

                then: "The user saved successfully and is in the database"
                charlie.errors.errorCount == 0
                charlie.id != null
                User.get(charlie.id).loginId == charlie.loginId
            }

            def "Updating a user and changing its properties" () {
                given: "A user"
                def existingUser = new User(
                    dateCreated:  new Date(),
                    password:  'password',
                    loginId: 'bookerc'
            )
            existingUser.save(failOnError: true)

            when: "The user has its properties changed"
            def chosenUser = User.get(existingUser.id)
            chosenUser.loginId = 'charlie'
            chosenUser.save(failOnError: true)


            then: "The user is updated in the database"
            User.get(existingUser.id).loginId =='charlie'
        }

        def "Deleting a user and making sure it's gone from the db" () {
            given: "A user"
            def newUser = new User(
                    dateCreated:  new Date(),
                    password:  'password',
                    loginId: 'charlieb'
            )
            newUser.save(failOnError: true)

            when: "The user is deleted"
            def chosenUser = User.get(newUser.id)
            chosenUser.delete(flush: true)


            then: "The user is removed from the database"
            !User.exists(chosenUser.id)
        }

        def "Saving a user with invalid properties causes an error"() {
            given: "A user that fails to validate"
            def newUser = new User(
                    dateCreated:  new Date(),
                    password:  'cb',
                    loginId: 'cb'
            )

            when:"The user is validated"
            newUser.validate()

            then:
            newUser.hasErrors()

            "size.toosmall" == newUser.errors.getFieldError("loginId").code
            "cb"== newUser.errors.getFieldError("loginId").rejectedValue
            !newUser.errors.getFieldError("password")
        }

        def "Recover from a failed validation"() {
            given: "A user that has an invalid property"
            def issues = new User(
                    dateCreated:  new Date(),
                    password:  'cb',
                    loginId: 'c'
            )
            assert issues.save() == null
            assert issues.hasErrors()

            when: "the invalid properties are fixed"
            issues.loginId = "Thomas"
            issues.validate()

            then: "the user saves with the updated info and validates"
            !issues.hasErrors()
            issues.save()
        }

}


