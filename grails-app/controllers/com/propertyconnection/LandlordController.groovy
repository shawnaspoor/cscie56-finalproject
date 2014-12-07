package com.propertyconnection

class LandlordController {
    static scaffold = true

    def homes() {
        def landlord = Landlord.findByLoginId(params.id)
        if(!landlord){
            render("You have no properties associated with your profile.")
        }else{
            [landlord : landlord]
        }
    }
}
