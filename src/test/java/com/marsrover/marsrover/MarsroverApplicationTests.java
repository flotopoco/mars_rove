package com.marsrover.marsrover;

import com.marsrover.marsrover.service.ReceiveInstructions;
import com.marsrover.marsrover.utils.CardinalDirections;
import com.marsrover.marsrover.utils.Utils;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import static org.hamcrest.CoreMatchers.containsString;


import com.marsrover.marsrover.model.*;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;



@SpringBootTest
class MarsroverApplicationTests {

	private Rover rover;
	private MarsMap mars;

	@Before
	public void initialise(){
		rover = new Rover(0,0,CardinalDirections.getNORTH());
		mars = new MarsMap(6);
	}

	//Test for analize command
	@Test
	public void testCommand(){
		assertThat(Utils.isValidCommand("FFFFBBRLRLR"),is(true));
		assertThat(Utils.isValidCommand("fffBlLrlRL"),is(true));
		assertThat(Utils.isValidCommand(""),is(false));
		assertThat(Utils.isValidCommand("FFFFBBI"),is(false));
		assertThat(Utils.isValidCommand(null),is(false));
	}

	//test for move
	@Test
	public void testMove(){
		initialise();
		rover.executeCommand("F",mars);
		assertThat(rover.getPosY(),is(1));
		rover.executeCommand("f",mars);
		assertThat(rover.getPosY(),is(2));
		rover.executeCommand("%",mars);
		assertThat(rover.getPosY(),is(2));
		rover.executeCommand("",mars);
		assertThat(rover.getPosY(),is(2));
		rover.executeCommand(null,mars);
		assertThat(rover.getPosY(),is(2));
		rover.executeCommand("B",mars);
		assertThat(rover.getPosY(),is(1));
		rover.executeCommand("b",mars);
		assertThat(rover.getPosY(),is(0));
		rover.executeCommand("B",mars);
		assertThat(rover.getPosY(),is(mars.getMapSize()));
		rover.executeCommand("",mars);
		assertThat(rover.getPosY(),is(mars.getMapSize()));
		rover.executeCommand("%",mars);
		assertThat(rover.getPosY(),is(mars.getMapSize()));
		rover.executeCommand(null,mars);
		assertThat(rover.getPosY(),is(mars.getMapSize()));
		rover.executeCommand("RF",mars);
		assertThat(rover.getPosX(),is(1));
		rover.executeCommand("f",mars);
		assertThat(rover.getPosX(),is(2));
		rover.executeCommand("",mars);
		assertThat(rover.getPosX(),is(2));
		rover.executeCommand("%",mars);
		assertThat(rover.getPosX(),is(2));
		rover.executeCommand(null,mars);
		assertThat(rover.getPosX(),is(2));
	}


	//test for turn Right
	@Test
	public void testTurnRight() {
		initialise();
		rover.executeCommand("R",mars);
		assertThat(rover.getFacing(),is(CardinalDirections.getEAST()));
		rover.executeCommand("r",mars);
		assertThat(rover.getFacing(),is(CardinalDirections.getSOUTH()));
		rover.executeCommand("%",mars);
		assertThat(rover.getFacing(),is(CardinalDirections.getSOUTH()));
		rover.executeCommand(null,mars);
		assertThat(rover.getFacing(),is(CardinalDirections.getSOUTH()));
		rover.executeCommand("",mars);
		assertThat(rover.getFacing(),is(CardinalDirections.getSOUTH()));
	}

	//test for turn Left
	@Test
	public void testTurnLeft() {
		initialise();
		rover.executeCommand("L",mars);
		assertThat(rover.getFacing(),is(CardinalDirections.getWEST()));
		rover.executeCommand("l",mars);
		assertThat(rover.getFacing(),is(CardinalDirections.getSOUTH()));
		rover.executeCommand("%",mars);
		assertThat(rover.getFacing(),is(CardinalDirections.getSOUTH()));
		rover.executeCommand(null,mars);
		assertThat(rover.getFacing(),is(CardinalDirections.getSOUTH()));
		rover.executeCommand("",mars);
		assertThat(rover.getFacing(),is(CardinalDirections.getSOUTH()));
	}

	//test for service
	@Test
	public void testServiceReceiveInstructions(){
		initialise();
		ReceiveInstructions receiveInstructions = new ReceiveInstructions();
		receiveInstructions.startProcess(rover,mars,"FFF");
		assertThat(receiveInstructions.getResult(),containsString("Command sent to rover"));
		receiveInstructions.startProcess(rover,mars,"FgFF");
		assertThat(receiveInstructions.getResult(),containsString("Invalid Command"));
		receiveInstructions.startProcess(rover,mars,"Command Null");
		assertThat(receiveInstructions.getResult(),containsString("Command Null"));


	}

}
