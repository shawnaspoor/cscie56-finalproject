import com.propertyconnection.*


class BootStrap {

    def springSecurityService

    def init = { servletContext ->
        environments {
            development {
                def userCount = User.count()
                println "User count is $userCount"
                if (userCount ==1 ) createSampleData()
            }
            test {
                if (!User.count()) createSampleData()
            }
        }

        // Admin user is required for all environments
        createAdminUserIfRequired()
    }

    private createSampleData() {
        println "Creating sample data"

        Date now = new Date()

        def tom = new Landlord(
                firstName: 'Tom',
                lastName:'Walker',
                loginId:'WomTalker',
                email: 'tomwalker@gmail.com',
                dateCreated: now,
                password: springSecurityService.encodePassword('Password1')
        ).save(failOnError: true)

        def dick = new Landlord(
                firstName: 'dick',
                lastName:'casablancas',
                loginId:'Dick',
                email: 'dick@gmail.com',
                dateCreated: now,
                password: springSecurityService.encodePassword('Password1')


        ).save(failOnError: true)

        def harry = new Landlord(
                firstName: 'Harry',
                lastName:'Trotter',
                loginId:'HarryTrotter',
                email: 'harrytrotter@gmail.com',
                dateCreated: now,
                password: springSecurityService.encodePassword('Password1')


        ).save(failOnError: true)

        def mary = new Landlord(
                firstName: 'Mary',
                lastName:'Walker',
                loginId:'MaryWalker',
                email: 'Marywalker@gmail.com',
                dateCreated: now,
                password: springSecurityService.encodePassword('Password1')


        ).save(failOnError: true)

        def susan = new Landlord(
                firstName: 'Susan',
                lastName:'Kicker',
                loginId:'Susan',
                email: 'susan@gmail.com',
                dateCreated: now,
                password: springSecurityService.encodePassword('Password1')


        ).save(failOnError: true)

        def sally = new Tenant(
                firstName: 'sally',
                lastName:'struthers',
                loginId:'sally',
                email: 'sally@gmail.com',
                dateCreated: now,
                password: springSecurityService.encodePassword('Password1')

        ).save(failOnError: true)

        def marcus = new Tenant(
                firstName: 'marcus',
                lastName:'smith',
                loginId:'marcussmith',
                email: 'marcussmith@gmail.com',
                dateCreated: now,
                password: springSecurityService.encodePassword('Password1')

        ).save(failOnError: true)

        def don = new Tenant(
                firstName: 'don',
                lastName:'johnson',
                loginId:'djohnson',
                email: 'djohnson@gmail.com',
                dateCreated: now,
                password: springSecurityService.encodePassword('Password1')

        ).save(failOnError: true)

        def john = new Tenant(
                firstName: 'john',
                lastName:'malbec',
                loginId:'johnmalbec',
                email: 'johnmalbec@gmail.com',
                dateCreated: now,
                password: springSecurityService.encodePassword('Password1')

        ).save(failOnError: true)

        def andy = new Tenant(
                firstName: 'Andrew',
                lastName:'reeves',
                loginId:'areeves',
                email: 'andrewreeves@gmail.com',
                dateCreated: now,
                password: springSecurityService.encodePassword('Password1')

        ).save(failOnError: true)

        def lyndeSt = new Home (
                propertyTitle:'Lynde St',
                streetAddress:'29 Lynde St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:2,
                baths:1,
                landlord: tom
        ).save()

        def oakgroveave = new Home (
                propertyTitle:'Oak Grove Ave',
                streetAddress:'1 Oak Grove Ave',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:1,
                baths:1,
                landlord: dick
        ).save()
        def islandhill = new Home (
                propertyTitle:'Island Hill Ave',
                streetAddress:'14 Island Hill Ave',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:1,
                baths:2,
                landlord: harry

        ).save()
        def mainst = new Home (
                propertyTitle:'Main St',
                streetAddress:'404 Lynde St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:1,
                baths:1,
                landlord: mary
        ).save()
        def westwyoming = new Home (
                propertyTitle:'West Wyoming',
                streetAddress:'504 West Wyoming St',
                //state nullable: false
                city: 'Melrose',
                zipcode:'02176',
                bedrooms:3,
                baths:3,
                landlord: tom
        ).save()

        def lyndeSO1 = new ServiceOrder(
                description: 'blocked toilet',
                location: 'bathroom',
                homes: lyndeSt,
                tenants: andy,
                landlord: tom
        ).save()

        def westWyomingSO1 = new ServiceOrder(
                description: 'mice in kitchen',
                location: 'kitchen',
                homes: westwyoming,
                tenants: john,
                landlord: tom
        ).save()

        def oakGroveSO1 = new ServiceOrder(
                description: 'bedroom door lock broken',
                location: 'bedroom',
                homes:  oakgroveave,
                tenants: sally,
                landlord: dick
        ).save()


        def payment1 = new Payment(
                landlord: dick,
                paymentId: '456asv',
                homes: oakgroveave,
                tenant: sally
        ).save()

        def payment2 = new Payment(
                landlord: tom,
                paymentId: '123asv',
                homes: westwyoming,
                tenant: andy
        ).save()

        tom.addToTenants(andy)
        tom.addToTenants(john)
        tom.save(failOnError: true)

        dick.addToTenants(sally)
        dick.save(failOnError: true)

        harry.addToTenants(marcus)
        harry.save(failOnError: true)

        mary.addToTenants(don)
        mary.save(failOnError: true)

        islandhill.setTenant(marcus)
        islandhill.save(failOnError: true)

        lyndeSt.setTenant(andy)
        lyndeSt.save(failOnError: true)

        oakgroveave.setTenant(sally)
        oakgroveave.save(failOnError: true)

        mainst.setTenant(don)
        mainst.save(failOnError: true)

        westwyoming.setTenant(john)
        westwyoming.save(failOnError: true)



    }

    private createAdminUserIfRequired() {
        if (!User.findByLoginId("admin")) {
            println "Fresh Database. Creating ADMIN user."
            def adminRole = new Role (authority: "ROLE_ADMIN").save(failOnError: true)
            def adminUser = new User(loginId: "admin", password: springSecurityService.encodePassword('Password1'),
            enabled: true).save(failOnError: true)
            UserRole.create adminUser, adminRole
        }
        else {
            println "Existing admin user, skipping creation"
        }
    }

}
