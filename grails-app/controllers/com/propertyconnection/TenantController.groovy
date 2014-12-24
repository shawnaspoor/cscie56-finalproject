package com.propertyconnection



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class TenantController {

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Tenant.list(params), model:[tenantInstanceCount: Tenant.count()]
    }

    def show(Tenant tenantInstance) {
        respond tenantInstance
    }

    def create() {
        respond new Tenant(params)
    }

    def register() {
        if(request.method == "POST"){
            def tenant = new Tenant(params)
            if(tenant.validate()){
                tenant.save()
                flash.message="You have successfully registered"
                redirect(uri: '/')
            }else {
                flash.message="There was a problem creating your account"
                return [tenant:tenant]
            }
        }

    }

    @Transactional
    def save(Tenant tenantInstance) {
        if (tenantInstance == null) {
            notFound()
            return
        }

        if (tenantInstance.hasErrors()) {
            respond tenantInstance.errors, view:'create'
            return
        }

        tenantInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'tenant.label', default: 'Tenant'), tenantInstance.id])
                redirect tenantInstance
            }
            '*' { respond tenantInstance, [status: CREATED] }
        }
    }

    def edit(Tenant tenantInstance) {
        respond tenantInstance
    }

    @Transactional
    def update(Tenant tenantInstance) {
        if (tenantInstance == null) {
            notFound()
            return
        }

        if (tenantInstance.hasErrors()) {
            respond tenantInstance.errors, view:'edit'
            return
        }

        tenantInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Tenant.label', default: 'Tenant'), tenantInstance.id])
                redirect tenantInstance
            }
            '*'{ respond tenantInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Tenant tenantInstance) {

        if (tenantInstance == null) {
            notFound()
            return
        }

        tenantInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Tenant.label', default: 'Tenant'), tenantInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'tenant.label', default: 'Tenant'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}

/*This is my custom code that I started with but had to abandon because I couldn't get a bunch of stuff to work.
But I wanted to make sure you saw the work I put in.

package com.propertyconnection

class TenantController {
    static scaffold = true

    def register() {
        if(request.method == "POST"){
            def tenant = new Tenant(params)
            if(tenant.validate()){
                tenant.save()
                flash.message="You have successfully registered"
                redirect(uri: '/')
            }else {
                flash.message="There was a problem creating your account"
                return [tenant:tenant]
            }
        }

    }

    def register2(TenantRegistrationCommand trc) {
        if(trc.hasErrors()) {
            render view:"register", model: [tenant: trc]
        }else{
            def tenant = new Tenant(trc.properties)
            if(tenant.validate() && tenant.save()) {
                flash.message="Welcome to Property Connection ${trc.firstName ?: trc.loginId}"
                redirect(uri:'/')
            }else{
                return[tenant: trc]
            }
        }
    }

    def profile(String id){
        def tenant = Tenant.findByLoginId(id)
        if(tenant) {
            return [profile: tenant]
        }else {
            response.sendError(404)
        }
    }

    def search() {

    }

    def results(String email) {
        def tenant = Tenant.where{
            email =~email
        }.list()
        return [tenant: tenant,
                term: params.email,
                totalTenants: Tenant.count()]
    }


}

class TenantRegistrationCommand {
    String firstName
    String lastName
    String email
    String password
    String passwordRepeat
    String loginId
    Date dateCreated
    byte[] photo

    static constraints = {
        importFrom Tenant
        password(size: 8..15, validator: {passwd, trc -> passwd !=trc.loginId} )
        passwordRepeat (nullable: false, validator: {passwd2, trc -> return passwd2 == trc.password})
    }

}
*/