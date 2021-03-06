package Minesweeper.Core;

import java.util.ArrayList;
import java.util.List;

public class PlayField {

	public Controller controller;
	public List<List<Field>> fields = new ArrayList<>();
	public final int DIMENSIONS;

	public PlayField(Controller ctrl, int dimensions) {
		controller = ctrl;
		DIMENSIONS = dimensions;
	}

	public Field createField(int x, int y) {
		if(fields.size() <= x) {
			fields.add(new ArrayList<>());
		}
		fields.get(x).add(new Field(x, y));
		fields.get(x).get(y).addMouseListener(
				new FieldMouseListener(getFieldByCoordinates(x, y), controller));
		return fields.get(x).get(y);
	}

	public Field placeMine(Field field) {
		field.setMine(true);
		field.setText("mine");
		return field;
	}

	public Field tagField(Field field) {
		field.setTagged(!field.isTagged());
		return field;
	}
	
	public Field getFieldByCoordinates(int x, int y) {
		if(x >= 0 && x <= DIMENSIONS - 1 && y >= 0 && y <= DIMENSIONS - 1)
			return fields.get(x).get(y);
		return null;
	}

	public List<Field> getMines() {
		List<Field> mines = new ArrayList<>();
		for (List<Field> line : fields)
		{
			for (Field field : line) {
				if (field.isMine())
					mines.add(field);
			}
		}
		return mines;
	}

	public List<Field> getNeighbours(Field field) {
		List<Field> neighbours = new ArrayList<>();
		List<Field> definitiveNeighbours = new ArrayList<>();
		int x = field.getXCoordinate();
		int y = field.getYCoordinate();
		neighbours.add(getFieldByCoordinates(x-1,y));
		neighbours.add(getFieldByCoordinates(x-1,y-1));
		neighbours.add(getFieldByCoordinates(x-1,y+1));
		neighbours.add(getFieldByCoordinates(x,y-1));
		neighbours.add(getFieldByCoordinates(x,y+1));
		neighbours.add(getFieldByCoordinates(x+1,y));
		neighbours.add(getFieldByCoordinates(x+1,y-1));
		neighbours.add(getFieldByCoordinates(x+1,y+1));
		for(Field neighbour: neighbours)
		{
			if(neighbour != null)
				definitiveNeighbours.add(neighbour);
		}
		return definitiveNeighbours;
	}
}

