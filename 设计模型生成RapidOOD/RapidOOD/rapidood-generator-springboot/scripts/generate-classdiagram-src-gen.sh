#!/usr/bin/env bash
set -euo pipefail

ROOT="$(cd "$(dirname "$0")/.." && pwd)"
WORK="/tmp/rapidood-generator-build"
ANTLR_CP="$HOME/.m2/repository/org/xtext/antlr-generator/3.2.1/antlr-generator-3.2.1.jar:$HOME/.m2/repository/org/antlr/antlr-runtime/3.2/antlr-runtime-3.2.jar"

export JAVA_HOME="${JAVA_HOME:-$(/usr/libexec/java_home -v 17)}"
export MAVEN_OPTS="--add-opens=java.base/java.lang=ALL-UNNAMED"

echo "Preparing ASCII-only workspace at $WORK ..."
rm -rf "$WORK"
mkdir -p "$WORK"
rsync -a \
  --exclude target \
  --exclude .git \
  "$ROOT/" "$WORK/"

run_mwe2() {
  (cd "$WORK" && mvn -pl rapidood-classdiagram-model -Pxtext-generate generate-sources -DskipTests -q) || true
}

echo "[1/3] Running Xtext MWE2 (first pass)..."
run_mwe2

ANTLR_DIR="$WORK/rapidood-classdiagram-model/src-gen/com/rm2pt/rapidood/cd/parser/antlr/internal"
if [[ ! -f "$ANTLR_DIR/InternalClassDiagram.g" ]]; then
  echo "ERROR: MWE2 did not produce InternalClassDiagram.g"
  exit 1
fi

echo "[2/3] Running ANTLR Tool for lexer..."
(cd "$ANTLR_DIR" && java -cp "$ANTLR_CP" org.antlr.Tool InternalClassDiagram.g)

echo "[3/3] Running Xtext MWE2 (second pass)..."
run_mwe2

GENERATED="$WORK/rapidood-classdiagram-model/src-gen/com/rm2pt/rapidood/cd/ClassDiagramStandaloneSetupGenerated.java"
if [[ ! -f "$GENERATED" ]]; then
  echo "ERROR: MWE2 second pass did not produce ClassDiagramStandaloneSetupGenerated.java"
  exit 1
fi

echo "Copying src-gen back to project..."
rm -rf "$ROOT/rapidood-classdiagram-model/src-gen"
rsync -a "$WORK/rapidood-classdiagram-model/src-gen/" "$ROOT/rapidood-classdiagram-model/src-gen/"
if [[ -d "$WORK/rapidood-classdiagram-model/model/generated" ]]; then
  rsync -a "$WORK/rapidood-classdiagram-model/model/generated/" "$ROOT/rapidood-classdiagram-model/model/generated/"
fi
echo "src-gen generation completed ($(find "$ROOT/rapidood-classdiagram-model/src-gen" -name '*.java' | wc -l | tr -d ' ') Java files)."
