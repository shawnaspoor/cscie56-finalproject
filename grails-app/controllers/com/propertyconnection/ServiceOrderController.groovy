package com.propertyconnection

class ServiceOrderController {
    static scaffold = true

    static defaultAction = "home"

    def serviceOrderService

    def home() {
        if(!params.id){
            params.id = "shawna"
        }
        redirect(action:'listing', params: params)
    }

    def listing(String id) {
        def tenant = Tenant.findByLoginId(id)
        if(!tenant) {
            render("${tenant} Whoopsie")
            //response.sendError(404)
        }else {
            [tenant: tenant]

        }
    }

    def sos() {
        if(!session.tenant){
            redirect controller: "login", action: "form"
            return
        }else {
            render view: "listing", model:[tenant: session.tenant.refresh()]
        }
    }

    def createServiceOrder(String description, String location, String id) {
            try{
                def newServiceOrder = serviceOrderService.createServiceOrder(description, location, id)
                flash.message = "Added a new service order: ${newServiceOrder.description}"
            } catch (ServiceOrderException se) {
                flash.message = se.message
            }
            redirect(action: 'listing', id: id )
    }

    /*
    def createServiceOrder() {
        def tenant = Tenant.findByLoginId(params.id)
        Date today = new Date()
        if(tenant) {
            def serviceOrder = new ServiceOrder(params)
            //add landlord based on the tenant
            def landlord = tenant.getLandlord()
            serviceOrder.setLandlord(landlord)
            //add the home based on the tenant
            def home = tenant.getHomes()
            serviceOrder.setHomes(home)
            //add dateCreate value
            serviceOrder.setDateCreated(today)
            //create the service order and associate it with the tenant
            tenant.addToServiceOrders(serviceOrder)
            if (tenant.save()) {
                flash.message = "Your service order has been submitted"
            } else {
                flash.message = "Sorry there seems to be something wrong, try that again."
            }
        }else {
            flash.message="Sorry, your id is not recognized"
        }
        redirect(action: 'listing', id: params.id)
    }
*/
}

