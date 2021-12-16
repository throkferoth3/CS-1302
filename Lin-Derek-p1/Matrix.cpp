#include "Matrix.h"
#include <iostream>

using namespace std;

Matrix::Matrix(uint rows, uint cols) {
    arr2d = new double*[rows];
    arr2dRows = rows;
    arr2dCols = cols;
    for (uint i = 0; i < rows; i++) {
		arr2d[i] = new double[cols]; //creating array
        for (uint j = 0; j < cols; j++) {
            arr2d[i][j] = 0;
        }
	}
}

Matrix::Matrix(const Matrix & m) {
    arr2d = new double*[m.numRows()];
    arr2dRows = m.numRows();
    arr2dCols = m.numCols();
    for (uint i = 0; i < m.numRows(); i++) {
        arr2d[i] = new double[m.numCols()];
        for (uint j = 0; j < m.numCols(); j++) {
            arr2d[i][j] = m.at(i, j); //copying
        }
    }
}

Matrix::~Matrix() {
	for (uint i = 0; i < this->numRows(); i++) {
		delete[] arr2d[i];
	}
    delete[] arr2d;
}

Matrix Matrix::add(double s) const {
    Matrix retMatrix = *this;
    for (uint i = 0; i < this->numRows(); i++) {
        for (uint j = 0; j < this->numCols(); j++) {
            retMatrix.at(i, j) += s;
        }
    }
    return retMatrix;
}

Matrix Matrix::add(const Matrix & m) const {
    Matrix retMatrix = *this;
    for (uint i = 0; i < this->numRows(); i++) {
        for (uint j = 0; j < this->numCols(); j++) {
            retMatrix.at(i, j) += m.at(i, j);
        }
    }
    return retMatrix;
}

Matrix Matrix::subtract(double s) const {
    Matrix retMatrix = *this;
    for (uint i = 0; i < this->numRows(); i++) {
        for (uint j = 0; j < this->numCols(); j++) {
            retMatrix.at(i, j) -= s;
        }
    }
    return retMatrix;
}

Matrix Matrix::subtract(const Matrix & m) const {
    Matrix retMatrix = *this;
    for (uint i = 0; i < this->numRows(); i++) {
        for (uint j = 0; j < this->numCols(); j++) {
            retMatrix.at(i, j) -= m.at(i, j);
        }
    }
    return retMatrix;
}

Matrix Matrix::multiply(double s) const {
    Matrix retMatrix = *this;
    for (uint i = 0; i < this->numRows(); i++) {
        for (uint j = 0; j < this->numCols(); j++) {
            retMatrix.at(i, j) *= s;
        }
    }
    return retMatrix;
}

Matrix Matrix::multiply(const Matrix & m) const {
    Matrix retMatrix = Matrix(this->numRows(), m.numCols());
    for(int i = 0; i < this->numRows(); i++) {
        for(int j = 0; j < m.numCols(); j++) {
            for(int k = 0; k < this->numCols(); k++) { //since both matrices have this value
                retMatrix.at(i, j) += this->at(i, k) * m.at(k, j);
            }
        }
    }
    return retMatrix;
}

Matrix Matrix::divide(double s) const {
    Matrix retMatrix = *this;
    for (uint i = 0; i < this->numRows(); i++) {
        for (uint j = 0; j < this->numCols(); j++) {
            retMatrix.at(i, j) /= s;
        }
    }
    return retMatrix;
}

Matrix Matrix::t() const {
    Matrix retMatrix = Matrix(this->numCols(), this->numRows());
    for (uint i = 0; i < this->numRows(); i++) {
        for (uint j = 0; j < this->numCols(); j++) {
            retMatrix.at(j, i) = this->at(i, j);
        }
    }
    return retMatrix;
}

const uint Matrix::numRows() const {
    return arr2dRows;
}

const uint Matrix::numCols() const {
    return arr2dCols;
}

double & Matrix::at(uint row, uint col) {
    return arr2d[row][col];
}

const double & Matrix::at(uint row, uint col) const {
    return arr2d[row][col];
}

void Matrix::print() {
    for (int i = 0; i < this->numRows(); i++) {
        for (int j = 0; j < this->numCols(); j++) {
            cout << this->at(i, j) << "\t";
        }
        cout << endl;
    }
}
