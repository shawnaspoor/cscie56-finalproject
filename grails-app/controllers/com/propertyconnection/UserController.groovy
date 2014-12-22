package com.propertyconnection

class UserController {
    static scaffold = true

    def mailService



    def register(UserRegistrationCommand urc) {
        if(urc.hasErrors()) {
            render view:"/register", model: [user: urc]
        }else{
            if (params.selection =="landlord")
            {
                def landlord = new Landlord(urc.properties)
                if(landlord.validate() && landlord.save()) {
                    flash.message="Welcome to Property Connection ${urc.firstName ?: urc.loginId}"
                    redirect(uri:'/')
                }else{
                    return[landlord: urc]
                }
            }else{

                def tenant = new Tenant(urc.properties)
                if(tenant.validate() && tenant.save()) {
                    flash.message="Welcome to Property Connection ${urc.firstName ?: urc.loginId}"
                    redirect(uri:'/')
                }else{
                    return[tenant: urc]
                }
            }
        }
    }



}

class UserRegistrationCommand {
    String firstName
    String lastName
    String email
    String password
    String passwordRepeat
    String loginId
    Date dateCreated
    byte[] photo

    static constraints = {
        importFrom User
        password(size: 8..15, validator: {passwd, urc -> passwd !=urc.loginId} )
        passwordRepeat (nullable: false, validator: {passwd2, urc -> return passwd2 == urc.password})
    }

}
