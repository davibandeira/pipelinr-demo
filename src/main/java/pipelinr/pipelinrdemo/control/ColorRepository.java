package pipelinr.pipelinrdemo.control;

import org.springframework.data.jpa.repository.JpaRepository;

import pipelinr.pipelinrdemo.entity.Color;

/**
 * Enables database interaction with the 'colors' table.
 */
public interface ColorRepository extends JpaRepository<Color, Integer> {

}
