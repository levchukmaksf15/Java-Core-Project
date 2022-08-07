package javacorproject.ua.itea;

import java.awt.Color;

public class Checker {
	private Cell cell;
	private Color color;

	public Checker(Cell cell, Color color) {
		super();
		this.cell = cell;
		this.color = color;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public Cell getCell() {
		return cell;
	}

	public void setCell(Cell cell) {
		this.cell = cell;
	}
}
