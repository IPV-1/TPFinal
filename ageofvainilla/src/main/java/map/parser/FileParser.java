package map.parser;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import map.tiles.Tile;
import resource.Resource;

public class FileParser {
	
	private static List<List<Tile>> resultado = new ArrayList<List<Tile>>();

	public static List<List<Tile>> parse(String file) {
		
		BufferedReader br = null;
		try {
			String line;
			InputStream input = Resource.class.getResourceAsStream(file);
			br = new BufferedReader(new InputStreamReader(input));
			for (int y = 0; (line = br.readLine()) != null; y++) {
				process(line, y, resultado);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (br != null)
					br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
		
		return resultado;
	}
	
	private static void process(String line, int y, List<List<Tile>> resultado) {
		Scanner scanner = new Scanner(line);
		for (int x = 0; scanner.hasNext(); x++) {
			set(Tile.getTile(scanner.next()), x, y);
		}
		scanner.close();
	}
	
	private static void set(Tile tile, int x, int y) {
		if(resultado.size() == y) {
			resultado.add(new ArrayList<Tile>());
		}
		
		resultado.get(y).add(tile);
	}


}
