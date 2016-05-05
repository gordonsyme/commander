# commander

Sometimes you need to run arbitrary commands on a host over HTTP in the most insecure way possible

## Usage

```
$ lein run
```

Then:
```
$ curl http://localhost:2222/bin/echo?hello=world
out:
hello world


err:

```

The resource is used as the path to the command to execute, the query-string is
the arguments. Each argument is separated with `=`. Because.
