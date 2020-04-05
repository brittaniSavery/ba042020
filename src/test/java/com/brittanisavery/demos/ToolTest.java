package com.brittanisavery.demos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.brittanisavery.demos.models.Tool;

public class ToolTest {

    @Test
    public void Should_ReturnLadderTool_When_CodeIsLADW() throws Exception {
        Tool tool = new Tool("LADW");
        assertTrue(tool.getType().equals("Ladder"));
    }

    @Test
    public void Should_ReturnChainsawTool_When_CodeIsCHNS() throws Exception {
        Tool tool = new Tool("CHNS");
        assertTrue(tool.getType().equals("Chainsaw"));
    }

    @Test
    public void Should_ReturnRidgidJackhammer_When_CodeIsJAKR() throws Exception {
        Tool tool = new Tool("JAKR");
        assertTrue(tool.getType().equals("Jackhammer"));
        assertTrue(tool.getBrand().equals("Ridgid"));
    }

    @Test
    public void Should_ReturnDeWaltJackhammer_When_CodeIsJAKD() throws Exception {
        Tool tool = new Tool("JAKD");
        assertTrue(tool.getType().equals("Jackhammer"));
        assertTrue(tool.getBrand().equals("DeWalt"));
    }

    @Test
    public void Should_FailWithException_When_CodeIsNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            Tool tool = new Tool("ADBC");
            tool.getCode();
        });
    }
}