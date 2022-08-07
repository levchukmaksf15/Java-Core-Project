package javacorproject.ua.itea;

import java.awt.Color;
import java.util.Arrays;
import java.util.Objects;

import javax.swing.JButton;

public class Cell {
	private JButton button;
	private int[] coordinates = new int[2];
	private Color color;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + Arrays.hashCode(coordinates);
		result = prime * result + Objects.hash(color);
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Cell other = (Cell) obj;
		return Arrays.equals(coordinates, other.coordinates);
	}

	public Cell(JButton button, int[] coordinates, Color color) {
		super();
		this.button = button;
		this.coordinates = coordinates;
		this.color = color;
	}

	public Cell() {
		super();
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	public JButton getButton() {
		return button;
	}

	public JButton setButton(JButton button) {
		this.button = button;
		return button;
	}

	public int[] getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(int[] coordinates) {
		this.coordinates = coordinates;
	}

}
