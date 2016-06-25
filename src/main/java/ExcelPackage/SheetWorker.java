package ExcelPackage;

import FilePackage.FileWorker;
import ModelPackage.Model;
import ModelPackage.ModelExtended;
import MeteoUtilPackage.DataHandler;
import org.apache.poi.xssf.usermodel.XSSFSheet;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class SheetWorker {
    private static Date createSheetDate;
    private static XSSFSheet currentSheet;
    private static final int FIRST_ROW_OF_DATA = 3;
    private static final int LAST_ROW_OF_DATA = 27;

    public static Date getCreateSheetDate() {
        return createSheetDate;
    }

    public static XSSFSheet getCurrentSheet() {
        return currentSheet;
    }

    public static List<Model> getModels(XSSFSheet sheet) {
        calculateInternalEntity(sheet);
        return getModelsFromSheet();
    }

    public static ModelExtended getModelExtended(XSSFSheet sheet) {
        calculateInternalEntity(sheet);
        checkModelExtendedHeaders();
        return getModelExtendedFromSheet();
    }

    private static void checkModelExtendedHeaders() {
        DataHandler.checkAB28Cell(currentSheet);
        DataHandler.checkAB29Cell(currentSheet);
        DataHandler.checkAB30Cell(currentSheet);
        DataHandler.checkAB31Cell(currentSheet);
        DataHandler.checkI28Cell(currentSheet);
        DataHandler.checkI29Cell(currentSheet);
        DataHandler.checkK28Cell(currentSheet);
        DataHandler.checkK29Cell(currentSheet);
    }

    private static void calculateInternalEntity(XSSFSheet sheet) {
        catchNullArgument(sheet);
        currentSheet = sheet;
        createSheetDate = calculateSheetDate();
    }

    private static Date calculateSheetDate() {
        long dayOfWeekMs = 0;
        String dayOfWeek = currentSheet.getSheetName();
        Date date = FileWorker.getCreateFileDate();
        switch (dayOfWeek) {
            case "понедельник":
                dayOfWeekMs = 1000 * 60 * 60 * 24 * 7;
                break;
            case "вторник":
                dayOfWeekMs = 1000 * 60 * 60 * 24 * 6;
                break;
            case "среда":
                dayOfWeekMs = 1000 * 60 * 60 * 24 * 5;
                break;
            case "четверг":
                dayOfWeekMs = 1000 * 60 * 60 * 24 * 4;
                break;
            case "пятница":
                dayOfWeekMs = 1000 * 60 * 60 * 24 * 3;
                break;
            case "суббота":
                dayOfWeekMs = 1000 * 60 * 60 * 24 * 2;
                break;
            case "воскресенье":
                dayOfWeekMs = 1000 * 60 * 60 * 24;
                break;
        }
        long dateMs = date.getTime();
        dateMs = dateMs - dayOfWeekMs;
        return new Date(dateMs);
    }

    private static ModelExtended getModelExtendedFromSheet() {
        Date date = calculateSheetDate();
        Double minTempAir = DataHandler.getMinTempAir(currentSheet);
        Double minTempSoil = DataHandler.getMinTempSoil(currentSheet);
        Double maxTempAir = DataHandler.getMaxTempAir(currentSheet);
        Double averageTempAir = DataHandler.getAverageTemp(currentSheet);
        Double min2cm = DataHandler.getMin2cm(currentSheet);
        Double Precipitation00 = DataHandler.getPrecipitation00(currentSheet);
        Double Precipitation06 = DataHandler.getPrecipitation06(currentSheet);
        Double Precipitation12 = DataHandler.getPrecipitation12(currentSheet);
        Double Precipitation18 = DataHandler.getPrecipitation18(currentSheet);
        return new ModelExtended(date, minTempAir, minTempSoil, maxTempAir, averageTempAir, min2cm, Precipitation00, Precipitation06, Precipitation12, Precipitation18);
    }

    private static List<Model> getModelsFromSheet() {
        List<Model> result = new ArrayList<>();
        for (int i = FIRST_ROW_OF_DATA; i < LAST_ROW_OF_DATA; i++) {
            Model model;
            model = RowWorker.getModel(currentSheet.getRow(i));
            result.add(model);
        }
        return result;
    }

    private static void catchNullArgument(XSSFSheet sheet) {
        if (sheet == null) {
            throw new IllegalArgumentException("Can not get Models from null sheet");
        }
    }
}
