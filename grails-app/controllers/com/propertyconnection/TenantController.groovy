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
