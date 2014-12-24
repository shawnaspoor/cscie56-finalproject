package com.propertyconnection



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class LandlordController {

    def homeService

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Landlord.list(params), model:[landlordInstanceCount: Landlord.count()]
    }

    def show(Landlord landlordInstance) {
        respond landlordInstance
    }

    def create() {
        respond new Landlord(params)
    }

    @Transactional
    def save(Landlord landlordInstance) {
        if (landlordInstance == null) {
            notFound()
            return
        }

        if (landlordInstance.hasErrors()) {
            respond landlordInstance.errors, view:'create'
            return
        }

        landlordInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'landlord.label', default: 'Landlord'), landlordInstance.id])
                redirect landlordInstance
            }
            '*' { respond landlordInstance, [status: CREATED] }
        }
    }
//my custom controller
    def register() {
        if(request.method == "POST"){
            def landlord = new Landlord(params)
            if(landlord.validate()){
                landlord.save()
                flash.message="You have successfully registered"
                redirect(uri: '/')
            }else {
                flash.message="There was a problem creating your account"
                return [landlord:landlord]
            }
        }

    }
//my custom controller
    def homes(Long id) {
        def landlord = Landlord.findById(id)
        if(!landlord){
            render("You have no properties associated with your profile.")
        }else{
            [landlord : landlord]
        }
    }


//my custom controller
    def createHome(String propertyTitle, String streetAddress,
                   String city, String state, String zipcode, Integer bedrooms, Integer baths, Long id) {
        try{
            def newHome = homeService.createHome(propertyTitle, streetAddress,
                    city, state, zipcode, bedrooms, baths, id)
            flash.message = "Added a new home: ${newHome.propertyTitle}"
        } catch (HomeException he) {
            flash.message = he.message
        }
        redirect(action: 'homes', id: id )
    }

    def edit(Landlord landlordInstance) {
        respond landlordInstance
    }

    @Transactional
    def update(Landlord landlordInstance) {
        if (landlordInstance == null) {
            notFound()
            return
        }

        if (landlordInstance.hasErrors()) {
            respond landlordInstance.errors, view:'edit'
            return
        }

        landlordInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Landlord.label', default: 'Landlord'), landlordInstance.id])
                redirect landlordInstance
            }
            '*'{ respond landlordInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Landlord landlordInstance) {

        if (landlordInstance == null) {
            notFound()
            return
        }

        landlordInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Landlord.label', default: 'Landlord'), landlordInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'landlord.label', default: 'Landlord'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

/*This is my custom code that I started with but had to abandon because I couldn't get a bunch of stuff to work.
But I wanted to make sure you saw the work I put in

package com.propertyconnection

class LandlordController {
    static scaffold = true

    static defaultAction = "home"

    def homeService

    def home(String id) {
        def landlord = Landlord.findByLoginId(id)
        if(!id){
            id = "shawna"
        }
        redirect(action:'profile', id: id)
    }


    def homes(String id) {
        def landlord = Landlord.findByLoginId(id)
        if(!landlord){
            render("You have no properties associated with your profile.")
        }else{
            [landlord : landlord]
        }
    }

        def register() {
            if(request.method == "POST"){
                def landlord = new Landlord(params)
                if(landlord.validate()){
                    landlord.save()
                    flash.message="You have successfully registered"
                    redirect(uri: '/')
                }else {
                    flash.message="There was a problem creating your account"
                    return [landlord:landlord]
                }
            }

        }

        def register2(LandlordRegistrationCommand lrc) {
            if(lrc.hasErrors()) {
                render view:"register", model: [landlord: lrc]
            }else{
                def landlord = new Landlord(lrc.properties)
                if(landlord.validate() && landlord.save()) {
                    flash.message="Welcome to Property Connection ${lrc.firstName ?: lrc.loginId}"
                    redirect(uri:'/')
                }else{
                    return[landlord: lrc]
                }
            }
        }

        def profile(String id){
            def landlord = Landlord.findByLoginId(id)
            if(landlord) {
                return [profile: landlord]
            }else {
                response.sendError(404)
            }
        }




    }

    class LandlordRegistrationCommand {
        String firstName
        String lastName
        String email
        String password
        String passwordRepeat
        String loginId
        Date dateCreated
        byte[] photo

        static constraints = {
            importFrom Landlord
            password(size: 8..15, validator: {passwd, lrc -> passwd !=lrc.loginId} )
            passwordRepeat (nullable: false, validator: {passwd2, lrc -> return passwd2 == lrc.password})
        }

    }

 */
