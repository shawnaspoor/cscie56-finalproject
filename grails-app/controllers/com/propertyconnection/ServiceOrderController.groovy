package com.propertyconnection

class ServiceOrderController {
    static scaffold = true

    def listing() {
        def tenant = Tenant.findByLoginId(params.id)
        if(!tenant) {
            render("${tenant} Whoopsie")
            //response.sendError(404)
        }else {
            [tenant: tenant]

        }
    }

}

