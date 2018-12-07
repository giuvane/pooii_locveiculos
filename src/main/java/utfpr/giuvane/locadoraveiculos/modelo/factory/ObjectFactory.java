/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utfpr.giuvane.locadoraveiculos.modelo.factory;

import java.time.Clock;

/**
 *
 * @author Giuvane
 */
public class ObjectFactory {
    public static Object newInstance(String className)
    {
        Class clazz = null;
        
        try {
            clazz = Class.forName(className);
        }
        catch (ClassNotFoundException cnfe) {
            System.out.println("Não encontrada a classe com nome: " + className);
        }
        
        Object newObject = null;
        
        if (clazz != null) {
            try {
                newObject = clazz.newInstance();
            }
            catch (InstantiationException ie) {
                System.out.println("Não foi possível instanciar classe com nome: " + className);
            }
            
            catch (IllegalAccessException iae) {
                System.out.println("Impossível acessar classe com nome: " + className);
            }
        }
        
        return newObject;
    }
    
}
