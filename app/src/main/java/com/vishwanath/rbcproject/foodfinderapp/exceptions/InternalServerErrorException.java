package com.vishwanath.rbcproject.foodfinderapp.exceptions;


public class InternalServerErrorException extends HttpException {
    @Override
    public String getMessage() {
        return "500 Internal server error";
    }
}
