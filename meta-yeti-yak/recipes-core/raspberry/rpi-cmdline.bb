SUMMARY = "cmdline.txt for Raspberry Pi"
LICENSE = "Apache-2.0"
LIC_FILES_CHKSUM = "file://${COMMON_LICENSE_DIR}/Apache-2.0;md5=89aea4e17d99a7cacdbeed46a0096b10"

# Match all Pi machines (rpi, raspberrypi{,0-4}, etc.)
COMPATIBLE_MACHINE = "(raspberrypi|^rpi$)"

INHIBIT_DEFAULT_DEPS = "1"
inherit deploy nopackages

do_compile() {
    cat > ${WORKDIR}/cmdline.txt <<'EOF'
console=tty3 root=/dev/mmcblk0p2 rootfstype=ext4 fsck.repair=yes rootwait noswap fastboot fbcon=logo-pos:center quiet vt.global_cursor_default=0

EOF
}

do_deploy() {
    # deploy to BOTH places:
    install -d ${DEPLOYDIR} ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}
    install -m 0644 ${WORKDIR}/cmdline.txt ${DEPLOYDIR}/cmdline.txt
    install -m 0644 ${WORKDIR}/cmdline.txt ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}/cmdline.txt
}

addtask deploy before do_build after do_install
do_deploy[dirs] += "${DEPLOYDIR} ${DEPLOYDIR}/${BOOTFILES_DIR_NAME}"

PACKAGE_ARCH = "${MACHINE_ARCH}"
