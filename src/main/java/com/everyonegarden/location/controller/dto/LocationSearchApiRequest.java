package com.everyonegarden.location.controller.dto;

public record LocationSearchApiRequest(
        String address
) {
    public String eraseBlank(){
        return address.replace(" ","");
    }
}
