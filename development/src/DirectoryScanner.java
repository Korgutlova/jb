import org.apache.commons.io.FileUtils;
import org.apache.commons.io.filefilter.FalseFileFilter;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.apache.commons.io.filefilter.TrueFileFilter;
import org.apache.commons.io.filefilter.WildcardFileFilter;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

public class DirectoryScanner{

    private File inputDir;
    private File outputDir;
    private String mask;
    private int waitInterval;
    private boolean includeSubfolders;
    private boolean autoDelete;

    public DirectoryScanner(File inputDir, File outputDir, String mask, int waitInterval, boolean includeSubfolders, boolean autoDelete) {
        this.inputDir = inputDir;
        this.outputDir = outputDir;
        this.mask = mask;
        this.waitInterval = waitInterval;
        this.includeSubfolders = includeSubfolders;
        this.autoDelete = autoDelete;
    }

    public void scan(){
        
        //обрабокта поддиректорий
        IOFileFilter Filter = includeSubfolders ? TrueFileFilter.INSTANCE : FalseFileFilter.INSTANCE;
        //отбор файлов подходящих по маске
        Iterator<File> fileIterator = FileUtils.iterateFiles(inputDir, new WildcardFileFilter(mask), Filter);
        //обрабокта текущего файла
        while(fileIterator.hasNext()){
            File input = fileIterator.next();
            File output = new File(outputDir.getPath() + input.getPath().substring(inputDir.getPath().length()));

            try{
                if (output.exists()) {
                    FileUtils.deleteQuietly(output);
                }
                if (autoDelete) {
                    FileUtils.moveFile(input, output);
                } else {
                    FileUtils.copyFile(input, output);
                }
            } catch (IOException e) {
                break;
            }

            try{
                TimeUnit.SECONDS.sleep(waitInterval);
            } catch (InterruptedException e) {
                break;
            }

        }

    }
}
