package kr.hs.newsreader.domain.usecase

import kr.hs.newsreader.domain.repository.GoogleNewsRepository
import javax.inject.Inject

class LoadGoogleNews @Inject constructor(private val googleNewsRepository: GoogleNewsRepository) {
    suspend operator fun invoke() {
        googleNewsRepository.loadArticles()
    }
}