class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/tenant/$id" {
            controller="tenant"
            action="profile"
        }
        "/serviceorders" {
            controller="serviceOrder"
            action="sos"
        }

        "/"(view:"/index")
        "500"(view:'/error')
	}
}
