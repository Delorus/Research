package ru.sherb.research.struct.tree;

import org.junit.jupiter.api.Test;
import ru.sherb.research.struct.tree.PrettyFormatter.Leaf;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author maksim
 * @since 25.05.19
 */
public class PrettyFormatterTest {

    @Test
    public void formatSimpleLeaf() {
        var leaf = new Leaf("1");

        assertEquals("(1)", leaf.format());
    }

    @Test
    public void formatLeafWithIndent() {
        var leaf = new Leaf("1");
        leaf.setIndent(4);

        assertEquals("    (1)", leaf.format());
    }

    @Test
    public void formatLeafWIthPadding() {
        var leaf = new Leaf("1");
        leaf.setPadding(2);

        assertEquals("(  1  )", leaf.format());
    }
}