package xiaobai.search.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.commons.io.FilenameUtils;
import org.apache.lucene.document.Document;
import org.apache.lucene.document.Field;
import org.apache.lucene.document.Field.Index;
import org.apache.lucene.document.Field.TermVector;
import org.apache.lucene.index.CorruptIndexException;
import org.apache.lucene.index.IndexReader;
import org.apache.lucene.index.IndexWriter;
import org.apache.lucene.index.IndexWriterConfig;
import org.apache.lucene.store.Directory;
import org.apache.lucene.store.FSDirectory;
import org.apache.lucene.store.LockObtainFailedException;
import org.apache.lucene.util.Version;

import xiaobai.mybatis.model.FileInfo;


import com.chenlb.mmseg4j.analysis.MMSegAnalyzer;



public class IndexUtil {

        private static Directory directory = null;
        static String  relativelyPath=System.getProperty("user.dir");
        static {
                try {
                        directory = FSDirectory.open(new File(relativelyPath+"\\index"));
                } catch (IOException e) {
                        e.printStackTrace();
                }
        }



        public void createIndex(FileInfo fileInfo){
                IndexWriter writer = null;
                Document doc = null;
                try {
                        writer = new IndexWriter(directory,new IndexWriterConfig(Version.LUCENE_35, new MMSegAnalyzer("C:/Users/Administrator/Desktop/mmseg4j-1.8.5/data")));
                        //writer.deleteAll();
                        
                        doc = new Document();
                        doc.add(new Field("username",fileInfo.getUsername(),Field.Store.YES,Index.NOT_ANALYZED_NO_NORMS));
                        doc.add(new Field("fileName",fileInfo.getFileName(),Field.Store.YES,Index.NOT_ANALYZED_NO_NORMS));
                        doc.add(new Field("description",fileInfo.getDescription(),Field.Store.YES,Index.ANALYZED));
                        doc.add(new Field("keyword",fileInfo.getKeyword(),Field.Store.YES,Index.ANALYZED));
                        doc.add(new Field("classname",fileInfo.getKeyword(),Field.Store.YES,Index.NOT_ANALYZED_NO_NORMS));
                        
                        
                        
                        writer.addDocument(doc);
                        
                        
                } catch (CorruptIndexException e) {
                        e.printStackTrace();
                } catch (LockObtainFailedException e) {
                        e.printStackTrace();
                } catch (IOException e) {
                        e.printStackTrace();
                }finally{
                        if(writer != null)
                                try {
                                        writer.close();
                                } catch (CorruptIndexException e) {
                                        e.printStackTrace();
                                } catch (IOException e) {
                                        e.printStackTrace();
                                }
                }
                
                
                
                
        }
        
        
        
        
        
        
        
}