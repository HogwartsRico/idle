package com.away1994.tsp.feature.structure;

import com.away1994.tsp.feature.structure.ps.graph.ProjectStructureGraph;
import com.away1994.tsp.feature.structure.ps.scan.FileScan;
import org.junit.Test;

import java.io.FileWriter;

import static com.away1994.constants.TestConstants.TEST_RESOURCES_PATH;

public class ProjectStructureTest {

    @Test
    public void psTest1() throws Exception {
        ProjectStructureGraph graph = FileScan.scanProject("/Users/away/Desktop/ios_hello");
        System.out.println(graph.toString());
    }

    @Test
    public void psDotTest1() throws Exception {
        ProjectStructureGraph graph = FileScan.scanProject(TEST_RESOURCES_PATH + "test");
        System.out.println(graph.dotString());

        FileWriter fw = new FileWriter(TEST_RESOURCES_PATH + "out/psDotTest1.dot");
        fw.write(graph.dotString());
        fw.flush();
        fw.close();
    }

    @Test
    public void psDotTest2() throws Exception {
        ProjectStructureGraph graph = FileScan.scanProject("/Users/away/Desktop/ios_hello");
        System.out.println(graph.dotString());

        FileWriter fw = new FileWriter(TEST_RESOURCES_PATH + "out/psDotTest2.dot");
        fw.write(graph.dotString());
        fw.flush();
        fw.close();
    }
}
