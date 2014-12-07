package com.propertyconnection
class PhotoUploadCommand {
    byte[] photo
    String loginId
}

class ImageController {
    def upload(PhotoUploadCommand puc) {
        def tenant = Tenant.findByLoginId(puc.loginId)
        tenant.photo = puc.photo
        redirect controller: "tenant", id: puc.loginId
    }

    def form() {
        [tenantList : Tenant.list() ]
    }


    def renderImage(String id){
        def tenant = Tenant.findByLoginId(id)
        if(tenant?.photo){
            response.setContentLength(tenant.photo.size())
            response.outputStream.write(tenant.photo)
        }else{
            response.sendError(404)
        }
    }
}
