package Controller;

import FilePackage.FilesWorker;
import ModelPackage.Model;
import ModelPackage.ModelCleaner;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ModelWorkerServlet extends HttpServlet {
    private static final String UPLOAD_DIR = "uploads";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String applicationPath = request.getServletContext().getRealPath("");
        String uploadFilePath = applicationPath + File.separator + UPLOAD_DIR;
        File[] files = FilesWorker.getFiles(uploadFilePath);
        List<Model> models = FilesWorker.getModels(files);
        ModelCleaner.removeNullRecords(models);
        List<String> errors = ModelCleaner.checkErrors(models);
        List<String> warnings = ModelCleaner.checkWarnings(models);
        for (Model model :models) {
            System.out.println(model);
        }
        request.setAttribute("errors", errors);
        request.setAttribute("warnings", warnings);
        request.getRequestDispatcher("model_complete.jsp").forward(request, response);
    }
}
