# Schakel LTO uit tijdens GCC build
EXTRA_OECONF:append = " --disable-lto"
# Zorg dat bootstrap niet met exotische flags bouwt (houd het simpel)
EXTRA_OEMAKE:append = " BOOT_CFLAGS='-O2' STAGE1_CFLAGS='-O2' "
