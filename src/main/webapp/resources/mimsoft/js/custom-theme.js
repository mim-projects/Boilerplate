OnLoadWindow.push(() => {
    const navigationDrawer = document.getElementById("navigation_drawer")
    const navigationDrawerButton = document.getElementById("navigation_button")
    if (navigationDrawer === null && navigationDrawerButton === null) return
    navigationDrawerButton.onclick = () => {
        navigationDrawerButton.classList.toggle("opened")
        const isOpen = navigationDrawerButton.classList.contains("opened")
        navigationDrawerButton.setAttribute('aria-expanded', "" + isOpen)
        if (isOpen) navigationDrawer.classList.add("active_navigation_drawer")
        else navigationDrawer.classList.remove("active_navigation_drawer")
    }
})