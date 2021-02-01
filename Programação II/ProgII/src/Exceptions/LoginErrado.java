/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Exceptions;

/**
 *
 * @author Demo
 */
public class LoginErrado extends Exception {
    public LoginErrado(String msg) {
        super(msg);
    }
}
