package com.brittanisavery.demos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.brittanisavery.demos.models.Tool;

public class ToolTest {

    @Test
    public void Should_ReturnLadderTool_When_CodeIsLADW() throws Exception {
        Tool tool = new Tool("LADW");
        assertEquals(tool.getType(), "Ladder");
    }

    @Test
    public void Should_ReturnChainsawTool_When_CodeIsCHNS() throws Exception {
        Tool tool = new Tool("CHNS");
        assertEquals(tool.getType(), "Chainsaw");
    }

    @Test
    public void Should_ReturnRidgidJackhammer_When_CodeIsJAKR() throws Exception {
        Tool tool = new Tool("JAKR");
        assertEquals(tool.getType(), "Jackhammer");
        assertEquals(tool.getBrand(), "Ridgid");
    }

    @Test
    public void Should_ReturnDeWaltJackhammer_When_CodeIsJAKD() throws Exception {
        Tool tool = new Tool("JAKD");
        assertEquals(tool.getType(), "Jackhammer");
        assertEquals(tool.getBrand(), "DeWalt");
    }

    @Test
    public void Should_ThrowException_When_CodeIsNotFound() {
        assertThrows(IllegalArgumentException.class, () -> {
            Tool tool = new Tool("ADBC");
            tool.getCode();
        });
    }
}