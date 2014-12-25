package com.propertyconnection

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class ServiceOrderController {

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]
    def serviceOrderService
    def springSecurityService
    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond ServiceOrder.list(params), model:[serviceOrderInstanceCount: ServiceOrder.count()]
    }

    def show(ServiceOrder serviceOrderInstance) {
        respond serviceOrderInstance
    }

    /*def create() {
        respond new ServiceOrder(params)
    }*/
    /*my code */
    def listing() {
        def user = springSecurityService.currentUser
        def id = user.id
        def tenant = Tenant.findById(id)
        def landlord = Landlord.findById(id)
        if(!user) {
            render("${user} Whoopsie")
            //response.sendError(404)
        }else {
            if(tenant){
                [tenant:tenant]
            }
            if(landlord){
                [landlord:landlord]
            }


        }
    }
    /*my code */
    def sos() {
        if(!session.user){
            redirect controller: "login", action: "form"
            return
        }else {
            render view: "listing", model:[tenant: session.user.refresh()]
        }
    }
    /*my code */
    def createServiceOrder(String description, String location, Long id) {
        try{
            def newServiceOrder = serviceOrderService.createServiceOrder(description, location, id)
            flash.message = "Added a new service order: ${newServiceOrder.description}"
        } catch (ServiceOrderException se) {
            flash.message = se.message
        }
        redirect(action: 'listing', id: id )
    }



    @Transactional
    def save(ServiceOrder serviceOrderInstance) {
        if (serviceOrderInstance == null) {
            notFound()
            return
        }

        if (serviceOrderInstance.hasErrors()) {
            respond serviceOrderInstance.errors, view:'create'
            return
        }

        serviceOrderInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'serviceOrder.label', default: 'ServiceOrder'), serviceOrderInstance.id])
                redirect serviceOrderInstance
            }
            '*' { respond serviceOrderInstance, [status: CREATED] }
        }
    }

    def edit(ServiceOrder serviceOrderInstance) {
        respond serviceOrderInstance
    }

    @Transactional
    def update(ServiceOrder serviceOrderInstance) {
        if (serviceOrderInstance == null) {
            notFound()
            return
        }

        if (serviceOrderInstance.hasErrors()) {
            respond serviceOrderInstance.errors, view:'edit'
            return
        }

        serviceOrderInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'ServiceOrder.label', default: 'ServiceOrder'), serviceOrderInstance.id])
                redirect (controller:"serviceOrder", action:"listing", id:"${serviceOrderInstance.tenantsId}")

            }
            '*'{ respond serviceOrderInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(ServiceOrder serviceOrderInstance) {

        if (serviceOrderInstance == null) {
            notFound()
            return
        }

        serviceOrderInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'ServiceOrder.label', default: 'ServiceOrder'), serviceOrderInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'serviceOrder.label', default: 'ServiceOrder'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
/*
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

}
*/

