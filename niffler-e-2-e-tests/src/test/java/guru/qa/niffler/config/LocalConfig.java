package guru.qa.niffler.config;

enum LocalConfig implements Config {
  INSTANCE;

  @Override
  public String frontUrl() {
    return "http://auth.niffler.dc:9000/login";
  }

  @Override
  public String spendUrl() {
    return "http://127.0.0.1:8093/";
  }
}
