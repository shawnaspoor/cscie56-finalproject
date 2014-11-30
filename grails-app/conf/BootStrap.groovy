import com.propertyconnection.Home
import com.propertyconnection.Landlord
import com.propertyconnection.Payment
import com.propertyconnection.ServiceOrder
import com.propertyconnection.Tenant
import grails.plugin.cache.SerializableByteArrayOutputStream

class BootStrap {
    def init = { servletContext ->
        environments {
            development {
                def LLcount = Landlord.count()
                println "landlord count is $LLcount"
                if (LLcount ==1 ) createSampleData()
            }
            test {
                if (!Landlord.count()) createSampleData()
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
                password: 'Password1'
        ).save(failOnError: true)

        def dick = new Landlord(
                firstName: 'dick',
                lastName:'casablancas',
                loginId:'Dick',
                email: 'dick@gmail.com',
                dateCreated: now,
                password: 'Password1'


        ).save(failOnError: true)

        def harry = new Landlord(
                firstName: 'Harry',
                lastName:'Trotter',
                loginId:'HarryTrotter',
                email: 'harrytrotter@gmail.com',
                dateCreated: now,
                password: 'Password1'


        ).save(failOnError: true)

        def mary = new Landlord(
                firstName: 'Mary',
                lastName:'Walker',
                loginId:'MaryWalker',
                email: 'Marywalker@gmail.com',
                dateCreated: now,
                password: 'Password1'


        ).save(failOnError: true)

        def susan = new Landlord(
                firstName: 'Susan',
                lastName:'Kicker',
                loginId:'Susan',
                email: 'susan@gmail.com',
                dateCreated: now,
                password: 'Password1'


        ).save(failOnError: true)

        def sally = new Tenant(
                firstName: 'sally',
                lastName:'struthers',
                loginId:'sally',
                email: 'sally@gmail.com',
                dateCreated: now,
                password: 'Password1'

        ).save(failOnError: true)

        def marcus = new Tenant(
                firstName: 'marcus',
                lastName:'smith',
                loginId:'marcussmith',
                email: 'marcussmith@gmail.com',
                dateCreated: now,
                password: 'Password1'

        ).save(failOnError: true)

        def don = new Tenant(
                firstName: 'don',
                lastName:'johnson',
                loginId:'djohnson',
                email: 'djohnson@gmail.com',
                dateCreated: now,
                password: 'Password1'

        ).save(failOnError: true)

        def john = new Tenant(
                firstName: 'john',
                lastName:'malbec',
                loginId:'johnmalbec',
                email: 'johnmalbec@gmail.com',
                dateCreated: now,
                password: 'Password1'

        ).save(failOnError: true)

        def andy = new Tenant(
                firstName: 'Andrew',
                lastName:'reeves',
                loginId:'areeves',
                email: 'andrewreeves@gmail.com',
                dateCreated: now,
                password: 'Password1'

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

        islandhill.addToTenant(marcus)
        islandhill.save(failOnError: true)

        lyndeSt.addToTenant(andy)
        lyndeSt.save(failOnError: true)

        oakgroveave.addToTenant(sally)
        oakgroveave.save(failOnError: true)

        mainst.addToTenant(don)
        mainst.save(failOnError: true)

        westwyoming.addToTenant(john)
        westwyoming.save(failOnError: true)



    }

    private createAdminUserIfRequired() {
        println "Creating admin user"
        if (!Landlord.findByLoginId("admin")) {
            println "Fresh Database. Creating ADMIN user."

            new Landlord(loginId: "admin", password: "password1",  firstName: 'Shawna',
                    lastName:'reeves',
                    email: 'shawna@gmail.com').save(failOnError: true)
        }
        else {
            println "Existing admin user, skipping creation"
        }
    }

}
