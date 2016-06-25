package FilePackage;

import java.io.File;
import java.io.FileFilter;

public class ExtFilter implements FileFilter{
    private static final char CORRECT_FIRST_CHARACTER_IN_FILENAME = '2';
    private String ext;

    public ExtFilter(String ext) {
        this.ext = ext;
    }

    @Override
    public boolean accept(File pathname) {
        boolean rightExtension = getExtension(pathname).equals(ext);
        boolean rightFileName = getFirstSymbol(pathname) == CORRECT_FIRST_CHARACTER_IN_FILENAME;
        return rightExtension && rightFileName;
    }

    private String getExtension(File pathname) {
        String filename = pathname.getName();
        int i = filename.lastIndexOf('.');
        return filename.substring(i + 1).toLowerCase();
    }

    private char getFirstSymbol(File pathname) {
        String filename = pathname.getName();
        return filename.charAt(0);
    }
}
