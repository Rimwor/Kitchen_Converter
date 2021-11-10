package fr.afpa.kitchen_converter.operations;

import android.content.Context;

import java.util.HashMap;
import java.util.Map;

import fr.afpa.kitchen_converter.model.UnitManager;

/**
 * Conversion class
 * UNITS CONVERSION
 * Based on :
 * @author FAYOLLE Marc
 */
public class Convert {
    private static Map<String, Double> conversionTable = new HashMap<String, Double>();
    // static works as a constructor in a static class
//    static
//    {
//        conversionTable.put("Kilogram", Double.valueOf(1000));
//        conversionTable.put("Gram", Double.valueOf(1));
//        conversionTable.put("Litre", Double.valueOf(1000));
//        conversionTable.put("Millitre", Double.valueOf(1));
//    }

    /**
     * Returns a double for the << amount >> unit << source >> converted to unit << target >>
     * @param source
     * @param target
     * @param amount
     * @return le montant en devise cible
     */
    public static double convertir(String source, String target, double amount)
    {
        double sourceAmount = conversionTable.get(source);
        double targetAmount = conversionTable.get(target);
        double conversionAmount = targetAmount/sourceAmount ;
        return (amount / conversionAmount) ;
    }

    /**
     * Assessor of the association table of units
     * @return a reference on the unit table
     */
    public static Map<String, Double> getConversionTable(Context context)
    {
        if (Convert.conversionTable.isEmpty()) {
            Convert.conversionTable = UnitManager.getAll(context);
        }
        return Convert.conversionTable;
    }

}
