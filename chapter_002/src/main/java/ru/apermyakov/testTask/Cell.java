	package ru.apermyakov.testtask;

	/**
	* Сlass for modulate chess cell.
	*
	* @author apermyakov
	* @version 1.0
	* @since 20.10.2017
	*/
	public class Cell {

		/**
		* Initial array of x.
		*/
		private String[] x = new String[] {"A", "B", "C", "D", "E", "F", "G", "H"};

		/**
		* Initial array of y.
		*/
		private int[] y = new int[] {1, 2, 3, 4, 5, 6, 7, 8};

		/**
		* Initial x position.
		*/
		private int xCoord;

		/**
		* Initial y position.
		*/
		private int yCoord;

		/**
		* Design cell when we use understandable to any person coordinates.
		*
		* @author apermyakov
		* @param horizontal x dispatch of the cell
		* @param vertical y dispatch of the cell
		* @since 20.10.2017
		*/
		public Cell(String horizontal, int vertical) {
			for (int index = 0; index < x.length; index++) {
				if (horizontal.equals(x[index])) {
					this.xCoord = index;
					break;
				}
			}
			for (int index = 0; index < y.length; index++) {
				if (vertical == y[index]) {
					this.yCoord = index;
					break;
				}
			}
		}

		/**
		* Design cell when we use in back of our programm.
		*
		* @author apermyakov
		* @param xCoord x dispatch of the cell
		* @param yCoord y dispatch of the cell
		* @since 20.10.2017
		*/
		public Cell(int xCoord, int yCoord) {
			this.xCoord = xCoord;
			this.yCoord = yCoord;
		}

		/**
		* Method for get x of cell.
		*
		* @author apermyakov
		* @return x coordinate of cell
		* @since 20.10.2017
		*/
		public int getX() {
			return this.xCoord;
		}

		/**
		* Method for get y of cell.
		*
		* @author apermyakov
		* @return y coordinate of cell
		* @since 20.10.2017
		*/
		public int getY() {
			return this.yCoord;
		}
	}