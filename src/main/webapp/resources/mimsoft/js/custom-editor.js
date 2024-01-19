OnLoadWindow.push(() => {
    const customButton = document.getElementById("custom-button")
    customButton.innerHTML = `<svg xmlns="http://www.w3.org/2000/svg" width="18" height="18" style="fill: var(--text-color-secondary)" viewBox="0 0 24 24"><path d="M6.852,23.438a3.612,3.612,0,0,1-2.121-.7A3.57,3.57,0,0,1,3.4,18.684L4.57,15.065,1.49,12.813A3.625,3.625,0,0,1,3.63,6.261H7.4l1.145-3.57a3.627,3.627,0,0,1,6.906,0h0L16.6,6.261H20.37a3.625,3.625,0,0,1,2.139,6.552L19.43,15.065,20.6,18.684A3.626,3.626,0,0,1,15,22.719l-3-2.206L9,22.72A3.619,3.619,0,0,1,6.852,23.438ZM3.63,9.261a.626.626,0,0,0-.37,1.131l3.956,2.891a1.5,1.5,0,0,1,.542,1.672l-1.5,4.65a.626.626,0,0,0,.966.7l3.889-2.861a1.5,1.5,0,0,1,1.778,0l3.889,2.86a.625.625,0,0,0,.966-.7l-1.5-4.65a1.5,1.5,0,0,1,.542-1.672l3.955-2.891a.626.626,0,0,0-.369-1.131H15.5a1.5,1.5,0,0,1-1.428-1.042L12.6,3.607a.626.626,0,0,0-1.192,0L9.925,8.219A1.5,1.5,0,0,1,8.5,9.261Z"/></svg>`
    customButton.onclick = () => {
        const _editor = PF("editor").editor
        _editor.format('background', 'var(--primary-color)')
        _editor.format('color', 'var(--primary-color-text)')
        _editor.format('bold', true)
    }

    // TODO: PENDIENTE CAMBIAR IMAGE_BASE64 A URL
    // const imageUploadURL = window.location.href.split("/").slice(0, 4).join("/") + "/api/images/upload"
    // Quill.register("modules/imageUploader", ImageUploader)
    // Quill.update({
    //     modules: {
    //         simpleUploadAdapter: { uploadUrl: imageUploadURL }
    //     }
    // })
})