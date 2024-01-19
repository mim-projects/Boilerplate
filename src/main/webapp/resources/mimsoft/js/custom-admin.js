const uploadChangeImageProject = async (input_id, server_api) => {
    const input = document.getElementById(input_id)
    const data = new FormData()
    data.append("upload", input.files[0], "IMG_" + new Date().getMilliseconds() + ".JPEG")
    const result = await fetch(server_api, {
        method: "post", body: data
    }).then(result => result.json())

    if (result.url) {
        let image = input.parentElement.parentElement.childNodes[2].childNodes[1].childNodes[1]
        if (image === undefined) {
            const parent = input.parentElement.parentElement.childNodes[2].childNodes[1]
            parent.childNodes.forEach(item => item.remove())
            const nodeImage = document.createElement("img")
            nodeImage.src = result.url
            nodeImage.style.objectFit = "cover"
            parent.appendChild(nodeImage)
        } else image.src = result.url
    }
}