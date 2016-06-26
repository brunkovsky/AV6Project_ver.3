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
        List<String> result = generateErrorsList(models);
        removeEmptyRecords(result);
        return result;
    }

    private static List<String> generateErrorsList(List<Model> models) {
        List<String> result = new ArrayList<>();
        for (Model model :models) {
            result.add(Checker.checkWindSpeed(model));
            result.add(Checker.checkWindRush(model));
            result.add(Checker.checkWindVisibility(model));
            result.add(Checker.checkOctantsNumerator(model));
            result.add(Checker.checkOctantsDenominator(model));
            result.add(Checker.checkCloudiness(model));
            result.add(Checker.checkTemperature(model));
            result.add(Checker.checkDewPointTemperature(model));
            result.add(Checker.checkRelativityHumidity(model));
        }
        return result;
    }

    private static void removeEmptyRecords(List<String> result) {
        Iterator<String> iterator = result.iterator();
        while (iterator.hasNext()) {
            if (iterator.next().equals("")) {
                iterator.remove();
            }
        }
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
        private static final double MIN_DEW_POINT_TEMPERATURE = -50;
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

        private static String checkWindVisibility(Model model) {
            if (model.getVisibility() != null && (model.getVisibility() < 0 || model.getVisibility() > MAX_WIND_VISIBILITY)) {
                return "FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Visibility in column 'E' is not valid. Equals: " + model.getVisibility();
            }
            return "";
        }

        private static String checkOctantsNumerator(Model model) {
            if (model.getOctantsNumerator() != null && (model.getOctantsNumerator() < 0 || model.getOctantsNumerator() > MAX_OCTANTS)) {
                return "FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Octants Numerator in column 'I' is not valid. Equals: " + model.getOctantsNumerator();
            }
            return "";
        }

        private static String checkOctantsDenominator(Model model) {
            if (model.getOctantsDenominator() != null && (model.getOctantsDenominator() < 0 || model.getOctantsDenominator() > MAX_OCTANTS)) {
                return "FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Octants Numerator in column 'I' is not valid. Equals: " + model.getOctantsDenominator();
            }
            return "";
        }

        private static String checkCloudiness(Model model) {
            if (model.getCloudiness() != null && (model.getCloudiness() < 0 || model.getCloudiness() > MAX_CLOUDINESS)) {
                return "FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " + SheetWorker.getCurrentSheet().getSheetName() + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Cloudiness in column 'K' is not valid. Equals: " + model.getCloudiness();
            }
            return "";
        }

        private static String checkTemperature(Model model) {
            if (model.getTemperature() != null && (model.getTemperature() < MIN_TEMPERATURE || model.getTemperature() > MAX_TEMPERATURE)) {
                return "FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Temperature in column 'L' is not valid. Equals: " + model.getTemperature();
            }
            return "";
        }

        private static String checkDewPointTemperature(Model model) {
            if (model.getDewPointTemperature() != null && (model.getDewPointTemperature() < MIN_DEW_POINT_TEMPERATURE || model.getDewPointTemperature() > MAX_DEW_POINT_TEMPERATURE)) {
                return "FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Dew point temperature in column 'M' is not valid. Equals: " + model.getDewPointTemperature();
            }
            return "";
        }

        private static String checkRelativityHumidity(Model model) {
            if (model.getRelativityHumidity() != null && (model.getRelativityHumidity() < 0 || model.getRelativityHumidity() > MAX_RELATIVITY_HUMIDITY)) {
                return "FileName: " + FileWorker.getCurrentFile().getName() + ", SheetName: " +  SheetWorker.getCurrentSheet().getSheetName()  + ", #Row: " + (RowWorker.getCurrentRow().getRowNum() + 1) + ", Relativity humidity in column 'N' is not valid. Equals: " + model.getRelativityHumidity();
            }
            return "";
        }
    }
}
