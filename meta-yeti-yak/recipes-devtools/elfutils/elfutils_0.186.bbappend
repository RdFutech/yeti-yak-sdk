# Schakel debuginfod via PACKAGECONFIG uit (netste manier)
PACKAGECONFIG:remove = "debuginfod"
PACKAGECONFIG:remove:class-native = "debuginfod"

# Extra veiligheid: forceer ook de configure-flag uit
EXTRA_OECONF:append = " --disable-debuginfod"
EXTRA_OECONF:append:class-native = " --disable-debuginfod"
