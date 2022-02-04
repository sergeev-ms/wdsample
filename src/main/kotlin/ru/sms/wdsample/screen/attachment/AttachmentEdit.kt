package ru.sms.wdsample.screen.attachment

import io.jmix.ui.screen.*
import ru.sms.wdsample.entity.Attachment

@UiController("wds_Attachment.edit")
@UiDescriptor("attachment-edit.xml")
@EditedEntityContainer("attachmentDc")
class AttachmentEdit : StandardEditor<Attachment>()