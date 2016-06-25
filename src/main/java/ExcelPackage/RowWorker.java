package ExcelPackage;

import ModelPackage.Model;
import MeteoUtilPackage.DataHandler;
import org.apache.poi.xssf.usermodel.XSSFRow;

import java.util.Date;

public class RowWorker {
    private static XSSFRow currentRow;
    private static Date currentRowTime;

    public static XSSFRow getCurrentRow() {
        return currentRow;
    }

    public static Date getCurrentRowTime() {
        return currentRowTime;
    }

    public static Model getModel(XSSFRow row) {
        catchNullArgument(row);
        currentRow = row;
        currentRowTime = calculateRowTime();
        return getModelFromRow();
    }

    private static Date calculateRowTime() {
        Date createSheetDate = SheetWorker.getCreateSheetDate();
        long longDate = createSheetDate.getTime();
        longDate = longDate - (3 - currentRow.getRowNum()) * 1000 * 60 * 60;
        return new Date(longDate);
    }

    private static Model getModelFromRow() {
        Date date = getCurrentRowTime();
        String windDirectionName = DataHandler.getWindDirectionName(currentRow);
        Integer windSpeed = DataHandler.getWindSpeed(currentRow);
        Integer  windRush = DataHandler.getWindRush(currentRow);
        Integer visibility = DataHandler.getVisibility(currentRow);
        Integer octantsNumerator = DataHandler.getOctantsNumerator(currentRow);
        Integer octantsDenominator  = DataHandler.getOctantsDenominator(currentRow);
        String cloudForm = DataHandler.getCloudForm(currentRow);
        Integer cloudiness = DataHandler.getCloudiness(currentRow);
        Double temperature = DataHandler.getTemperature(currentRow);
        Double dewPointTemperature = DataHandler.getDewPointTemperature(currentRow);
        Integer relativityHumidity = DataHandler.getRelativityHumidity(currentRow);
        Double absoluteHumidity = DataHandler.getAbsoluteHumidity(currentRow);
        Double atmospherePressure = DataHandler.getAtmospherePressure(currentRow);
        Double barometricTrend = DataHandler.getBarometricTrend(currentRow);
        Double qnhGpa = DataHandler.getQnhGpa(currentRow);
        Double qnhMm = DataHandler.getQnhMm(currentRow);
        Double qfe = DataHandler.getQfe(currentRow);
        return new Model(date, windDirectionName, windSpeed, windRush, visibility, octantsNumerator, octantsDenominator, cloudForm, cloudiness, temperature, dewPointTemperature, relativityHumidity, absoluteHumidity, atmospherePressure, barometricTrend, qnhGpa, qnhMm, qfe);
    }

    private static void catchNullArgument(XSSFRow row) {
        if (row == null) {
            throw new IllegalArgumentException("Can not get Model from null row");
        }
    }
}
