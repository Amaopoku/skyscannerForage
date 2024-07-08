@Override
public void run(HoenScannerConfiguration configuration, Environment environment) throws Exception {
    List<SearchResult> searchResults = new ArrayList<>();
    ObjectMapper mapper = new ObjectMapper();

    // Load rental cars
    File rentalCarsFile = new File(getClass().getClassLoader().getResource("rental_cars.json").getFile());
    List<SearchResult> rentalCars = Arrays.asList(mapper.readValue(rentalCarsFile, SearchResult[].class));

    // Load hotels
    File hotelsFile = new File(getClass().getClassLoader().getResource("hotels.json").getFile());
    List<SearchResult> hotels = Arrays.asList(mapper.readValue(hotelsFile, SearchResult[].class));

    searchResults.addAll(rentalCars);
    searchResults.addAll(hotels);

    // Register resources
    final SearchResource resource = new SearchResource(searchResults);
    environment.jersey().register(resource);
}
