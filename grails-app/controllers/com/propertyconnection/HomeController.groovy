package com.propertyconnection



import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class HomeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Home.list(params), model:[homeInstanceCount: Home.count()]
    }

    def show(Home homeInstance) {
        respond homeInstance
    }

    def create() {
        respond new Home(params)
    }

    @Transactional
    def save(Home homeInstance) {
        if (homeInstance == null) {
            notFound()
            return
        }

        if (homeInstance.hasErrors()) {
            respond homeInstance.errors, view:'create'
            return
        }

        homeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'home.label', default: 'Home'), homeInstance.id])
                redirect homeInstance
            }
            '*' { respond homeInstance, [status: CREATED] }
        }
    }

    def edit(Home homeInstance) {
        respond homeInstance
    }

    @Transactional
    def update(Home homeInstance) {
        if (homeInstance == null) {
            notFound()
            return
        }

        if (homeInstance.hasErrors()) {
            respond homeInstance.errors, view:'edit'
            return
        }

        homeInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Home.label', default: 'Home'), homeInstance.id])
                redirect homeInstance
            }
            '*'{ respond homeInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Home homeInstance) {

        if (homeInstance == null) {
            notFound()
            return
        }

        homeInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Home.label', default: 'Home'), homeInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'home.label', default: 'Home'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
/*

package com.propertyconnection

import grails.transaction.*


class HomeController {
    static scaffold = true

    static defaultAction = "home"

    def homeService

    def home() {
        if(!params.id){
            params.id = "shawna"
        }
        redirect(action:'listing', params: params)
    }

    def listing(String id) {
        def landlord = Landlord.findByLoginId(id)
        if(!landlord) {
            render("${landlord} Whoopsie")
            //response.sendError(404)
        }else {
            [landlord: landlord]

        }
    }
    def details(Long id) {
        def home = Home.findById(id)
        if(!home){
            render("Whoops that home doesn't appear to exist.")
        }else{
            [home: home]
        }
    }
    /*def updateHome() {
             def home = session.home?.attach()
             println home
             if(home) {
                 home.properties = params
                 if(home.save()){
                     flash.message = "Successfully updated"
                 }else {
                     flash.message = "There seems to be a problem with your change, no updates made."
                 }
                 [home: home]
             }else {
                 respon.sendError(4)
             }
         }
   /*
def updateHome(String propertyTitle, String streetAddress,
               String city, String state, String zipcode, Integer bedrooms, Integer baths, String _action_update,
               Integer id) {

    println "home bathrooms: ${baths}"
    println "home id in service: ${id}"
    println "property title: ${propertyTitle}"
    try{
        println "inside the try of controller"
        def updateHome = homeService.update(propertyTitle, streetAddress,
                city, state, zipcode, bedrooms, baths, id)
        flash.message = "Updated: ${updateHome.propertyTitle}"
    } catch (HomeException he) {
        flash.message = he.message
    }
    redirect(action: 'edit', id: id )
}


def edit(Long id) {
    def home = Home.findById(id)
    if(!home){
        render("Whoops that home doesn't appear to exist.")
    }else{
        [home: home]
    }
}

/*


 */