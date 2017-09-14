package forbes.componentns;

import org.apache.commons.lang3.StringUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.*;

public class HtmlTable extends net.thucydides.core.pages.components.HtmlTable{

    private final WebElement tableElement;
    private List<String> headings;

    public HtmlTable(final WebElement tableElement) {
        super(tableElement);
        this.tableElement = tableElement;
        this.headings = null;

    }

    public HtmlTable(final WebElement tableElement, List<String> headings) {
        super(tableElement,headings);
        this.tableElement = tableElement;
        this.headings = headings;
    }

    public static HtmlTable inTable(final WebElement table) {
        return new HtmlTable(table);
    }

    public static HtmlTableBuilder withColumn(String... headings) {
        return new HtmlTableBuilder(Arrays.asList(headings));
    }

    @Override
    public List<WebElement> getRowElementsFor(List<String> headings) {
        List<WebElement> rowCandidates = tableElement.findElements(By.xpath(".//tr[td]"));
        rowCandidates = stripHeaderRowIfPresent(rowCandidates, headings);
        return rowCandidates;
    }

    private List<WebElement> stripHeaderRowIfPresent(List<WebElement> rowCandidates, List<String> headings) {
        if (!rowCandidates.isEmpty()) {
            WebElement firstRow = rowCandidates.get(0);
            if (hasMatchingCellValuesIn(firstRow, headings)) {
                rowCandidates.remove(0);
            }
        }
        return rowCandidates;
    }

    private boolean hasMatchingCellValuesIn(WebElement firstRow, List<String> headings) {
        List<WebElement> cells = firstRow.findElements(By.xpath("./td"));
        for(int cellIndex = 0; cellIndex < headings.size(); cellIndex++) {
            if ((cells.size() < cellIndex) || (!cells.get(cellIndex).getText().equals(headings.get(cellIndex)))) {
                return false;
            }
        }
        return true;
    }

    public List<Map<Object, String>> readRowsFrom(WebElement table) {
        return new HtmlTable(table, headings).getRows();
    }

    public List<Map<Object, String>> getRows() {

        List<Map<Object, String>> results = new ArrayList<>();

        List<String> headings = getHeadings();
        List<WebElement> rows = getRowElementsFor(headings);

        for (WebElement row : rows) {
            List<WebElement> cells = cellsIn(row);
            if (enoughCellsFor(headings).in(cells)) {
                results.add(rowDataFrom(cells, headings));
            }
        }
        return results;
    }

    private List<WebElement> cellsIn(WebElement row) {
        return row.findElements(By.xpath("./td"));
    }

    private EnoughCellsCheck enoughCellsFor(List<String> headings) {
        return new EnoughCellsCheck(headings);
    }

    private Map<Object,String> rowDataFrom(List<WebElement> cells, List<String> headings) {
        Map<Object,String> rowData = new HashMap<>();

        int column = 0;
        for (String heading : headings) {
            String cell = cellValueAt(column++, cells);
            if (!StringUtils.isEmpty(heading)) {
                rowData.put(heading, cell);
                continue;
            }
            rowData.put(column, cell);
        }
        return rowData;
    }
    private String cellValueAt(final int column, final List<WebElement> cells) {
        return cells.get(column).getText();
    }

    private static class EnoughCellsCheck {
        private final int minimumNumberOfCells;

        private EnoughCellsCheck(List<String> headings) {
            this.minimumNumberOfCells = headings.size();
        }

        public boolean in(List<WebElement> cells) {
            return (cells.size() >= minimumNumberOfCells);
        }
    }

    public static class HtmlTableBuilder {
        private final List<String> headings;

        HtmlTableBuilder(List<String> headings) {
            this.headings = headings;
        }

        public List<Map<Object, String>> readRowsFrom(WebElement table) {
            return new HtmlTable(table, headings).getRows();
        }

        public net.thucydides.core.pages.components.HtmlTable inTable(WebElement table) {
            return new net.thucydides.core.pages.components.HtmlTable(table, headings);
        }
    }
}
