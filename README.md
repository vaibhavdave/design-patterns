# Design Patterns in Java

A hands-on, multi-module Gradle project for learning the classic Gang-of-Four design patterns —
plus a few enterprise/concurrency patterns that show up constantly in real Spring applications —
through runnable Java code, meaningful tests, and documentation with UML diagrams.

Every module is self-contained: its own `build.gradle.kts`, its own `README.md` (intent, a
real-world analogy, Mermaid UML diagrams, participants, when to use/avoid it, and how it relates
to other patterns in the repo), its own tests, and a runnable demo. Where Spring Boot genuinely
illustrates a pattern better than plain Java — a container managing singletons, generating
proxies, wiring strategies, publishing events — the module shows both the hand-rolled version and
the Spring-powered one side by side.

## How to use this repo

Work through the modules **in numeric order** — later modules assume you've seen the earlier
ones, and difficulty increases roughly monotonically. Each module's `README.md` is the primary
material; the code is there to run and experiment with, not just read.

```bash
./gradlew :01-singleton:test          # run one module's tests
./gradlew :01-singleton:run           # run one module's demo (bootRun for Spring Boot modules — see below)
./gradlew build                       # build + test every module in the repo
```

Requires JDK 21+ (a Gradle toolchain will provision one automatically if needed).

## Curriculum

### Creational — how objects get created

| # | Pattern | Difficulty | What it teaches |
|---|---|---|---|
| [01](01-singleton) | Singleton *(Spring)* | ★☆☆☆☆ | One instance, one access point — and why that's harder under concurrency than it looks |
| [02](02-factory-method) | Factory Method | ★★☆☆☆ | Let subclasses decide which concrete product to create |
| [03](03-abstract-factory) | Abstract Factory | ★★★☆☆ | Create whole *families* of related objects that must stay consistent |
| [04](04-builder) | Builder | ★★☆☆☆ | Assemble complex, immutable objects step by step instead of telescoping constructors |
| [05](05-prototype) | Prototype | ★★☆☆☆ | Clone pre-configured objects instead of building from scratch — and the shallow-vs-deep-copy trap |

### Structural — how objects are composed

| # | Pattern | Difficulty | What it teaches |
|---|---|---|---|
| [06](06-adapter) | Adapter | ★★☆☆☆ | Make an incompatible interface fit the one your code expects |
| [07](07-decorator) | Decorator | ★★☆☆☆ | Add behavior by wrapping, not subclassing — how `java.io` streams work |
| [08](08-facade) | Facade | ★☆☆☆☆ | Hide a messy subsystem behind one simple entry point |
| [09](09-composite) | Composite | ★★★☆☆ | Treat a single object and a tree of objects through the same interface |
| [10](10-proxy) | Proxy *(Spring)* | ★★★☆☆ | Control access to an object via a stand-in — lazy loading and Spring AOP |
| [11](11-bridge) | Bridge | ★★★☆☆ | Decouple an abstraction from its implementation so both can vary independently |
| [12](12-flyweight) | Flyweight | ★★★★☆ | Share immutable state across huge numbers of objects to save memory |

### Behavioral — how objects communicate

| # | Pattern | Difficulty | What it teaches |
|---|---|---|---|
| [13](13-strategy) | Strategy *(Spring)* | ★★☆☆☆ | Swap an algorithm at runtime via composition, not inheritance |
| [14](14-observer) | Observer *(Spring)* | ★★★☆☆ | Notify interested parties of state changes without coupling to them |
| [15](15-template-method) | Template Method | ★★☆☆☆ | Fix an algorithm's skeleton, let subclasses fill in the steps |
| [16](16-command) | Command | ★★★☆☆ | Turn a request into an object — enables undo, queuing, logging |
| [17](17-iterator) | Iterator | ★★☆☆☆ | What `for-each` actually desugars to |
| [18](18-state) | State | ★★★☆☆ | Replace state-tracking conditionals with polymorphism |
| [19](19-chain-of-responsibility) | Chain of Responsibility *(Spring-ish)* | ★★★☆☆ | Pass a request along a chain of handlers until one deals with it — how filters/interceptors work |
| [20](20-mediator) | Mediator | ★★★☆☆ | Route communication through a coordinator instead of a fully-connected object graph |
| [21](21-memento) | Memento | ★★★☆☆ | Snapshot and restore state without breaking encapsulation |
| [22](22-visitor) | Visitor | ★★★★☆ | Add new operations to a class hierarchy without modifying it — double dispatch |
| [23](23-interpreter) | Interpreter | ★★★★★ | Build and evaluate a tiny expression language — the idea behind parsers and rule engines |

### Capstones — enterprise & concurrency patterns that tie it together

| # | Pattern | Difficulty | What it teaches |
|---|---|---|---|
| [24](24-dependency-injection) | Dependency Injection & IoC | ★★★★☆ | The principle underneath every "(Spring)" module above, from manual wiring to a container |
| [25](25-repository-dao) | Repository / DAO | ★★★★☆ | Hide persistence behind a collection-like interface — Spring Data generates the implementation |
| [26](26-mvc) | MVC | ★★★★☆ | Separate state, presentation, and input handling — classic desktop MVC and Spring REST MVC |
| [27](27-producer-consumer) | Producer-Consumer | ★★★★☆ | Decouple work generation from work processing via a thread-safe queue, with real backpressure |
| [28](28-null-object) | Null Object | ★☆☆☆☆ | Replace absent collaborators with a do-nothing object so callers never write `if (x != null)` |

## Project structure

```
design-patterns/
├── build.gradle.kts              # shared conventions: Java 21 toolchain, JUnit5 + AssertJ
├── settings.gradle.kts           # declares all 28 modules
├── gradle/libs.versions.toml     # version catalog (Spring Boot BOM, H2, etc.)
├── 01-singleton/
│   ├── build.gradle.kts
│   ├── README.md                 # intent, analogy, Mermaid UML, when to use/avoid
│   └── src/
│       ├── main/java/...
│       └── test/java/...
├── 02-factory-method/
...
└── 27-producer-consumer/
```

## Why Gradle multi-module

Each pattern lives in complete isolation — its own dependencies, its own test suite, its own
runnable entry point — while sharing one consistent toolchain and testing setup via the root
`build.gradle.kts`. You can build, test, or run any single module without touching the rest of
the repo, which mirrors how you'd actually explore an unfamiliar pattern: one focused example at
a time.
