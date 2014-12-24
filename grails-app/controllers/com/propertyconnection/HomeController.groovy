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
   /**/
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
                  flash.message = message(code: 'default.updated.message', args: [message(code: 'home.label ', default: 'Home'), homeInstance.id])
                  redirect homeInstance
              }
              '*'{ respond homeInstance, [status: OK] }
          }
      }
    */
      @Transactional
      def delete(Home homeInstance) {

          if (homeInstance == null) {
              notFound()
              return
          }

          homeInstance.delete flush:true
      }

      protected void notFound() {
          request.withFormat {
              form multipartForm {
                  flash.message = message(code: 'default.not.found.message', args: [message(code: ' ', default: 'Home'), params.id])
                  redirect action: "index", method: "GET"
              }
              '*'{ render status: NOT_FOUND }
          }
      }

}