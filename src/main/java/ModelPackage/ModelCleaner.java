package ModelPackage;

import ExcelPackage.RowWorker;
import ExcelPackage.SheetWorker;
import FilePackage.FileWorker;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ModelCleaner {

    public static void removeNullRecords(List<Model> models) {
        Iterator<Model> iterator = models.iterator();
        while (iterator.hasNext()) {
            Model model = iterator.next();
            if (model.getWindSpeed() == null
                    && model.getWindRush() == null
                    && model.getVisibility() == null
                    && model.getOctantsNumerator() == null
                    && model.getOctantsDenominator() == null
                    && model.getCloudiness() == null
                    && model.getTemperature() == null
                    && model.getDewPointTemperature() == null
                    && model.getRelativityHumidity() == null
                    && model.getAbsoluteHumidity() == null
                    && model.getAtmospherePressure() == null
                    && model.getBarometricTrend() == null
                    && model.getQnhGpa() == null
                    && model.getQnhMm() == null
                    && model.getQfe() == null) {
                iterator.remove();
            }
        }
    }

    public static List<String> checkErrors(List<Model> models) {
        List<String> result = new ArrayList<>();
        for (Model model :models) {
            result.add(Checker.checkWindSpeed(model));
            result.add(Checker.checkWindRush(model));
        }
        return result;
    }

    public static List<String> checkWarnings(List<Model> models) {
        return null;
    }

    private static class Checker {
        private static final int MAX_WIND_SPEED = 50;
        private static final int MIN_WIND_RUSH = 7;
        private static final int MAX_WIND_RUSH = 50;
        private static final int MAX_WIND_VISIBILITY = 10000;
        private static final int MAX_OCTANTS = 8;
        private static final int MAX_CLOUDINESS = 6000;
        private static final double MIN_TEMPERATURE = -50;
        private static final double MAX_TEMPERATURE = 50;
        private static final double MIN_DEW_POINT_TEMPERATURE= -50;
        private static final double MAX_DEW_POINT_TEMPERATURE = 50;
        private static final int MAX_RELATIVITY_HUMIDITY = 100;
        private static final double MAX_ABSOLUTE_HUMIDITY = 103.3;
        private static final double MIN_ATMOSPHERE_PRESSURE = 700;
        private static final double MAX_ATMOSPHERE_PRESSURE = 800;
        private static final double MIN_BAROMETRIC_TREND = -10;
        private static final double MAX_BAROMETRIC_TREND = 10;
        private static final double MIN_QNH_GPA = 970;
        private static final double MAX_QNH_GPA = 1050;
        private static final double MIN_QNH_MM = 700;
        private static final double MAX_QNH_MM = 800;
        private static final double MIN_QFE = 970;
        private static final double MAX_QFE = 1050;
        private static final double MAX_PRECIPITATION = 100;

        private static String checkWindSpeed(Model model) {
            if (model.getWindSpeed() != null && (model.getWindSpeed() < 0 || model.getWindSpeed() > MAX_WIND_SPEED)) {
                return "FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Wind speed in column 'C' is not valid. Equals: " + model.getWindSpeed();
            }
            return "";
        }

        private static String checkWindRush(Model model) {
            if (model.getWindRush() != null && (model.getWindRush() < MIN_WIND_RUSH || model.getWindRush() > MAX_WIND_RUSH)) {
                return "FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Wind rush in column 'D' is not valid. Equals: " + model.getWindRush();
            }
            return "";
        }
    }
}
