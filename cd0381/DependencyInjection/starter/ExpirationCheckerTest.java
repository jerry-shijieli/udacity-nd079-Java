import java.nio.file.Path;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class ExpirationCheckerTest {
  public static void main(String[] args) {

    FakeClock fakeClock = new FakeClock();
    Map<Path, Instant> fakeModifiedTimes = new HashMap<>();
    FakeMetadataFetcher fakeMetadataFetcher = new FakeMetadataFetcher(fakeModifiedTimes);

    Path expired = Path.of("/test/expired");
    Path notExpired = Path.of("/test/not-expired");
    fakeModifiedTimes.put(expired, fakeClock.instant().minus(Duration.ofDays(31)));
    fakeModifiedTimes.put(notExpired, fakeClock.instant().minus(Duration.ofDays(27)));

    // TODO: Change this code to create an ExpirationChecker using the FakeClock and
    //       FakeMetadataFetcher.
    ExpirationChecker checker = new ExpirationChecker();

    List<Path> expiredFiles =
        checker.getExpiredFiles(List.of(expired, notExpired), Duration.ofDays(30));

    assert expiredFiles.equals(List.of(expired));
  }
}
