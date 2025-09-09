SUMMARY = "Qualcomm Atheros Open Powerline Toolkit (open-plc-utils)"
HOMEPAGE = "https://github.com/qca/open-plc-utils"
LICENSE = "BSD-3-Clause-Clear"
LIC_FILES_CHKSUM = "file://LICENSE;md5=7d83a9e9a9788beb9357262af385f6c7"

SRC_URI = "git://github.com/qca/open-plc-utils.git;branch=master;protocol=https \
"
SRCREV = "${AUTOREV}"
S = "${WORKDIR}/git"

inherit pkgconfig

# Forceer de OE toolchain (en voorkom host gcc/strip)
EXTRA_OEMAKE = "\
  CC='${CC}' \
  CPP='${CPP}' \
  AR='${AR}' \
  LD='${LD}' \
  STRIP='${STRIP}' \
  CFLAGS='${CFLAGS} ${TARGET_CC_ARCH}' \
  LDFLAGS='${LDFLAGS}' \
"

# Als het project helper-binaries voor de build zou genereren:
EXTRA_OEMAKE += "BUILD_CC='${BUILD_CC}' BUILD_CFLAGS='${BUILD_CFLAGS}'"

do_configure[noexec] = "1"

do_compile() {
    # -e laat env/EXTRA_OEMAKE overrulen wat in Makefiles staat
    oe_runmake -e V=1
}

do_install() {
    if oe_runmake -n install >/dev/null 2>&1; then
        oe_runmake -e install DESTDIR="${D}" PREFIX="${prefix}" BINDIR="${bindir}"
    else
        install -d ${D}${bindir}
        for d in plc nvm pib ether slac tools; do
            if [ -d "$d" ]; then
                find "$d" -maxdepth 1 -type f -perm -111 -exec install -m0755 {} ${D}${bindir} \;
            fi
        done
    fi
    # (optionele manpages zou je hier kunnen installeren zoals eerder getoond)
}

FILES:${PN} += "${bindir}/*"
