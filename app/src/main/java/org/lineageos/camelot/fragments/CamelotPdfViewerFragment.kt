/*
 * SPDX-FileCopyrightText: 2024 The LineageOS Project
 * SPDX-License-Identifier: Apache-2.0
 */

package org.lineageos.camelot.fragments

import android.os.Build
import android.os.Bundle
import android.view.View
import androidx.annotation.RequiresExtension
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.fragment.app.activityViewModels
import androidx.pdf.viewer.fragment.PdfViewerFragment
import androidx.pdf.viewer.fragment.pdfName
import org.lineageos.camelot.ext.updateMargin
import org.lineageos.camelot.viewmodels.PdfViewModel

@RequiresExtension(extension = Build.VERSION_CODES.S, version = 13)
class CamelotPdfViewerFragment : PdfViewerFragment() {
    private val pdfViewModel by activityViewModels<PdfViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fastScrollView = view.findViewById<View>(androidx.pdf.R.id.fast_scroll_view)

        ViewCompat.setOnApplyWindowInsetsListener(fastScrollView) { v, windowInsets ->
            val insets = windowInsets.getInsets(WindowInsetsCompat.Type.systemBars())

            v.updateMargin(
                insets,
                bottom = true,
            )

            windowInsets
        }
    }

    override fun onLoadDocumentSuccess() {
        super.onLoadDocumentSuccess()

        pdfViewModel.setPdfName(pdfName)
    }

    override fun onLoadDocumentError(error: Throwable) {
        super.onLoadDocumentError(error)

        pdfViewModel.setPdfName(null)
    }
}
